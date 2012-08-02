/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package support;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import org.apache.http.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author hdwjy
 */
public class ClientKeyWord {
    static String url="http://www.mediafactory.co.jp/bunkoj/top/index#new_release";
    
    
    public static void main(String[] arg) throws Exception{
        getXml(url,false);
    }

    public final static String getXml(String url, boolean nolink) throws Exception {
        String retString = null;

        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            httpclient.addRequestInterceptor(new HttpRequestInterceptor() {

                public void process(final HttpRequest request,
                        final HttpContext context) throws HttpException,
                        IOException {
                    if (!request.containsHeader("Accept-Encoding")) {
                        request.addHeader("Accept-Encoding", "gzip");
                    }
                }
            });

            httpclient.addResponseInterceptor(new HttpResponseInterceptor() {

                public void process(final HttpResponse response,
                        final HttpContext context) throws HttpException,
                        IOException {
                    HttpEntity entity = response.getEntity();
                    Header ceheader = entity.getContentEncoding();
                    if (ceheader != null) {
                        HeaderElement[] codecs = ceheader.getElements();
                        for (int i = 0; i < codecs.length; i++) {
                            if (codecs[i].getName().equalsIgnoreCase("gzip")) {
                                response.setEntity(new GzipDecompressingEntity(
                                        response.getEntity()));
                                return;
                            }
                        }
                    }
                }
            });

            HttpResponse response = null;
            if (nolink) {
                //代理
                HttpHost proxy = new HttpHost("127.0.0.1", 8118);
                httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
                // 目标地址
                HttpGet httpget = new HttpGet(url);
                response = httpclient.execute(httpget);
            } else {
                HttpGet httpget = new HttpGet(url);
                response = httpclient.execute(httpget);
            }


            // Execute HTTP request
//			System.out.println("executing request " + httpget.getURI());

//			System.out.println("----------------------------------------");
//			System.out.println(response.getStatusLine());
//			System.out.println(response.getLastHeader("Content-Encoding"));
//			System.out.println(response.getLastHeader("Content-Length"));
//			System.out.println("----------------------------------------");

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                String content = EntityUtils.toString(entity);
                retString = content;
//				System.out.println(content);
//				System.out.println("----------------------------------------");
//				System.out.println("Uncompressed size: " + content.length());
            }

        } finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }


        return retString;
    }

    static class GzipDecompressingEntity extends HttpEntityWrapper {

        public GzipDecompressingEntity(final HttpEntity entity) {
            super(entity);
        }

        @Override
        public InputStream getContent() throws IOException,
                IllegalStateException {

            // the wrapped entity's getContent() decides about repeatability
            InputStream wrappedin = wrappedEntity.getContent();

            return new GZIPInputStream(wrappedin);
        }

        @Override
        public long getContentLength() {
            // length of ungzipped content is not known
            return -1;
        }
    }
}
