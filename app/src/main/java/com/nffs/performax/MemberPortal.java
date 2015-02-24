package com.nffs.performax;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class MemberPortal extends Fragment {

    WebView webview;
    ProgressBar spinner;

    String url = "https://secure.nffs.com/member_portal/?csrf_key=WLaWu2FW&afterAppAd=1#/login";

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
     }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_member_portal, container, false);





        webview = (WebView) rootView.findViewById(R.id.webView);



        startWebView(url);




        webview.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });




        return rootView;
    }



    private void startWebView(String url) {

        //Create new webview Client to show progress dialog
        //When opening a url or click on link

        webview.setWebViewClient(new WebViewClient() {
            ProgressDialog progressDialog;

            //If you will not use this method url links are opened in new browser not in webview
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            //Show loader on url load
            public void onLoadResource(WebView view, String url) {
                if (progressDialog == null) {
                    // in standard case YourActivity.this
                    progressDialog = new ProgressDialog(getActivity());
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();
                }
            }

            public void onPageFinished(WebView view, String url) {
                try {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                       // progressDialog = null;
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

        });

        CookieManager.getInstance().setAcceptCookie(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(url);
    }

    public void onBackPressed() {
        if(webview.canGoBack()) {
            webview.goBack();
        } else {
            // Let the system handle the back button
            super.getActivity().onBackPressed();
        }
    }
}
