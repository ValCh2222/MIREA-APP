package com.mirea.chubuka_v_a.mireaapp_android.ui.Browser;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;

import com.mirea.chubuka_v_a.mireaapp_android.R;

public class WebViewFragment extends Fragment {
    private WebView webView;
    private EditText searchInput;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_web_view, container, false);
        view.findViewById(R.id.search_button).setOnClickListener(this::onSearchButtonClick);
        searchInput = (EditText) view.findViewById(R.id.url_input);

        webView = (WebView) view.findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        TextView text = (TextView) view.findViewById(R.id.textView2);
        text.setText("http://"+ searchInput.getText().toString());
        return view;
    }

    private void onSearchButtonClick(View view){

        String info = "http://"+ searchInput.getText().toString();
        webView.loadUrl(info);

    }
}