package com.gothere.gothere;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.gothere.gothere.adapters.RVAdapter;
import com.gothere.gothere.models.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;


public class MainActivity extends Activity {

    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    RVAdapter adapter;
    List<Item> items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycler);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);


        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                refreshItems();
            }
        });
        refreshItems();

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                items.remove(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());

            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

    }

    void refreshItems() {

        new AsyncTask<String, Void, String>() {
            @Override
            protected void onPreExecute() {
                //mDialog = ProgressDialog.show(MainActivity.this,
                //       "Load in progress", "Wait ...", true, true);
            }

            @Override
            protected String doInBackground(String... params) {
                //String realUrl = URL.replace("$QUERY$", "Android");
                String realUrl = "http://54.233.115.236:80/gothere/Item/";
                return requestContent(realUrl);
            }

            @Override
            protected void onPostExecute(String res) {
                items = new ArrayList<Item>();

                DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
                //2016-03-28T22:33:32.818776Z

                if (res != null) {
                    try {
                        JSONArray jsonItems = new JSONArray(res);
                        //{"id":1,"produto":"Balada VIP Sexta 28/03","descricao":"Balada VIP com MC Vacilão","valor_unitario":145.0,"data_criacao":"2016-03-28T22:33:32.818776Z","data_edicao":"2016-03-28T22:33:32.818811Z","data_vigencia":"2016-04-28T22:33:24Z","fornecedor":1}]

                        for (int i = 0; i < jsonItems.length(); i++) {
                            JSONObject itemJson = jsonItems.getJSONObject(i);
                            int id = itemJson.getInt("_id");
                            String produto = itemJson.getString("produto");
                            String descricao = itemJson.getString("descricao");
                            Double valor_unitario = itemJson.getDouble("valor_unitario");

                            Date data_criacao = format.parse(itemJson.getString("data_criacao"));
                            Date data_edicao = format.parse(itemJson.getString("data_edicao"));
                            Date data_vigencia = format.parse(itemJson.getString("data_vigencia"));

                            Item item = new Item(id, produto, descricao,
                                    valor_unitario, data_criacao,
                                    data_edicao, data_vigencia);

                            items.add(item);

                        }


                    } catch (JSONException e) {
                        Log.e("WS", "Erro JSON", e);
                    } catch (ParseException e) {
                        Log.e("WS", "Erro parsing na data", e);
                    }

                    adapter = new RVAdapter(items);
                    mRecyclerView.setAdapter(adapter);

                }else{
                    Context context = getApplicationContext();
                    CharSequence text = "Erro ao atualizar os items.";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
                    toast.show();

                    Log.e("WS", "Erro ao obter resultado do WS");
                }

                onItemsLoadComplete();

            }

        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR); // we target SDK > API 11
    }

    void onItemsLoadComplete() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public String requestContent(String urlString) {
        URL url;
        InputStream in;
        HttpURLConnection urlConnection = null;
        String result = null;

        try {
            url =  new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream());
            result = convertStreamToString(in);

        }catch (MalformedURLException e){
            Log.e("WS", "URL mal formada" ,e );
        }
        catch (IOException e){
            Log.e("WS", "IO Exception" ,e );
        }
        catch (Exception e){
            Log.e("WS", "Outra exception" ,e );
        }
        finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }

        }
        return result;
    }

    public String convertStreamToString(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            Log.e("WS", "IOException no convertStreamToString", e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                Log.e("WS", "IOException no convertStreamToString", e);
            }
        }
        Log.i("WS", sb.toString());
        return sb.toString();
    }

}
