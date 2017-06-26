package travelapp.tamasya.view.utils;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import travelapp.tamasya.R;

/**
 * Created by Nirav Dangi on 29/06/16.
 *
 * All the common & utility methods are defined here. These methods can be used one or more times in each an every classes.
 */
public class CommonMethods {

    public static final void displayAlert(Context context, String title, String message) {

        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }

        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        try {
            builder.show();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final void displayMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static final void displayMessageLong(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static final void enableStrictMode(Class mClass) {
        if (hasGingerbread()) {
            StrictMode.ThreadPolicy.Builder threadPolicyBuilder =
                    new StrictMode.ThreadPolicy.Builder()
                            .detectAll()
                            .penaltyLog();
            StrictMode.VmPolicy.Builder vmPolicyBuilder =
                    new StrictMode.VmPolicy.Builder()
                            .detectAll()
                            .penaltyLog();

            if (hasHoneycomb()) {
                threadPolicyBuilder.penaltyFlashScreen();
                vmPolicyBuilder
                        .setClassInstanceLimit(mClass, 1);
            }
            StrictMode.setThreadPolicy(threadPolicyBuilder.build());
            StrictMode.setVmPolicy(vmPolicyBuilder.build());
        }
    }

    public static final boolean hasFroyo() {
        // Can use static final constants like FROYO, declared in later versions
        // of the OS since they are inlined at compile time. This is guaranteed behavior.
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
    }

    public static final boolean hasGingerbread() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
    }

    public static final boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    public static final boolean hasHoneycombMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1;
    }

    public static final boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    public static final boolean hasKitKat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public static final void loadImage(Context context, ImageView imageView, String url) {

//        try {
//            Picasso picasso = getPicassoInstance(context);
//            picasso.load(url)
//                    .placeholder(R.drawable.empty_photo)
//                    .into(imageView);
//        }catch (Exception e) {
//            e.printStackTrace();
//        }

        try {
            Glide.with(context)
                    .load(url)
                    .centerCrop()
                    .placeholder(R.drawable.empty_photo)
                    .crossFade()
                    .into(imageView);
        }catch (Exception e) {
            e.printStackTrace();
        }

//        Picasso.with(context).load(url).into(imageView);

    }

    public static final void loadImageHD(Context context, ImageView imageView, String url) {

//        try {
//            Picasso picasso = getPicassoInstance(context);
//            picasso.load(url)
//                    .placeholder(R.drawable.empty_photo)
//                    .into(imageView);
//        }catch (Exception e) {
//            e.printStackTrace();
//        }

        try {
            Glide.with(context)
                    .load(url)
                    .placeholder(R.drawable.empty_photo)
                    .crossFade()
                    .into(imageView);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static Picasso getPicassoInstance(Context context) {
//
//        Picasso.Builder builder = new Picasso.Builder(context);
//
//        try {
//
//            OkHttpClient.Builder okHttp = new OkHttpClient.Builder()
//                    .sslSocketFactory(getSSLConfig().getSocketFactory());
//            okHttp.readTimeout(1, TimeUnit.HOURS);
//            okHttp.connectTimeout(1, TimeUnit.HOURS);
//
//            OkHttp3Downloader okHttpDownloader = new OkHttp3Downloader(okHttp.build());
//
//            builder.downloader(okHttpDownloader);
//
//            return builder.build();
//        } catch (NoSuchAlgorithmException e) {
//            throw new IllegalStateException("Failure initializing default SSL context", e);
//        } catch (KeyManagementException e) {
//            throw new IllegalStateException("Failure initializing default SSL context", e);
//        } catch (GeneralSecurityException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }

    public static void loadBitmap(ImageView imageView, String imagePath, int maxWidth, int maxHeight) {
        BitmapWorkerTask task = new BitmapWorkerTask(imageView, imagePath, maxWidth, maxHeight);
        task.execute();
    }

    public static boolean haveNetworkConnection(Context context) {

        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo[] networkInfos = cm.getAllNetworkInfo();

        for (NetworkInfo ni : networkInfos) {


            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;

            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }

        return haveConnectedWifi || haveConnectedMobile;
    }

    public static List<String> extractUrls(String input) {
        List<String> result = new ArrayList<String>();

        // http//:google.com - 0
        // http//:www.google.com - 0
        // www.google.com - 1

        Pattern pattern = Pattern.compile(
                "\\b(((ht|f)tp(s?)\\:\\/\\/|~\\/|\\/))" +
                        "|(\\w+:\\w+@)?(([-\\w]+\\.)+(com|org|net|gov" +
                        "|mil|biz|info|mobi|name|aero|jobs|museum" +
                        "|travel|[a-z]{2}))(:[\\d]{1,5})?" +
                        "(((\\/([-\\w~!$+|.,=]|%[a-f\\d]{2})+)+|\\/)+|\\?|#)?" +
                        "((\\?([-\\w~!$+|.,*:]|%[a-f\\d{2}])+=?" +
                        "([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)" +
                        "(&(?:[-\\w~!$+|.,*:]|%[a-f\\d{2}])+=?" +
                        "([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)*)*" +
                        "(#([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)?\\b");

        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            result.add(matcher.group());
        }

        return result;
    };


    //==== This code will be moved =====//

    /**
     * Get a file path from a Uri. This will get the the path for Storage Access
     * Framework Documents, as well as the _data field for the MediaStore and
     * other file-based ContentProviders.
     *
     * @param context The context.
     * @param uri The Uri to query.
     * @author paulburke
     */
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
                // ExternalStorageProvider
                if (isExternalStorageDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];

                    if ("primary".equalsIgnoreCase(type)) {
                        return Environment.getExternalStorageDirectory() + "/" + split[1];
                    }

                    // TODO handle non-primary volumes
                }
                // DownloadsProvider
                else if (isDownloadsDocument(uri)) {

                    final String id = DocumentsContract.getDocumentId(uri);
                    final Uri contentUri = ContentUris.withAppendedId(
                            Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                    return getDataColumn(context, contentUri, null, null);
                }
                // MediaProvider
                else if (isMediaDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];

                    Uri contentUri = null;
                    if ("image".equals(type)) {
                        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(type)) {
                        contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if ("audio".equals(type)) {
                        contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }

                    final String selection = "_id=?";
                    final String[] selectionArgs = new String[] {
                            split[1]
                    };

                    return getDataColumn(context, contentUri, selection, selectionArgs);
                }
            }
            // MediaStore (and general)
            else if ("content".equalsIgnoreCase(uri.getScheme())) {
                return getDataColumn(context, uri, null, null);
            }
            // File
            else if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context The context.
     * @param uri The Uri to query.
     * @param selection (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    private static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }


    public static String getCreatedText(long created) {

        CharSequence result = DateUtils.getRelativeTimeSpanString(created*1000, System.currentTimeMillis(), 0);
        if (result!=null)
            return result.toString();

        return "";
    }

    public static void notifyUserForOrder(Context context) {
//
//        Intent intent = new Intent(context, MyOrdersActivity.class);
//        intent.putExtra("orderPlaced",orderPlaced);
//        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        String message = "TravelApp";

        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                context);
        Notification notification = builder
//                .setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.app_icon).setTicker(context.getResources().getString(R.string.app_name)).setWhen(0)
                .setAutoCancel(true).setContentTitle("Title")
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setDefaults(Notification.DEFAULT_LIGHTS| Notification.DEFAULT_SOUND)
                .setContentText(message).build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }

    public static void savePrefLocation(Context context, double lat, double lng) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("location", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lat", lat+"");
        editor.putString("lng", lng+"");
        editor.commit();
    }

    public static double getPrefLat(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("location", Context.MODE_PRIVATE);
        return Double.parseDouble(sharedPreferences.getString("lat", "-1"));
    }

    public static double getPrefLng(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("location", Context.MODE_PRIVATE);
        return Double.parseDouble(sharedPreferences.getString("lng", "-1"));
    }

    public static long getTimestampFromDate(String dateTime){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        formatter.setTimeZone(TimeZone.getTimeZone(TimeZone.getDefault().getDisplayName()));

        Date date = null;
        try {
            date = (Date)formatter.parse(dateTime);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Round to certain number of decimals
     *
     * @param d
     * @param decimalPlace
     * @return
     */
    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    public static String get10digitMobileNumber(String mobile){

        String code = mobile.substring(0, 4);

        if (code.equalsIgnoreCase("+521"))
            return mobile.substring(4, mobile.length());

        code = code.substring(0, 3);

        if (code.equalsIgnoreCase("+52"))
            return mobile.substring(3, mobile.length());

        return mobile.substring(0,10);
    }

    public static void overrideFonts(final Context context, final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideFonts(context, child);
                }
            } else if (v instanceof TextView ) {
                ((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), "Avenir-Book.ttf"));
            }

        } catch (Exception e) {
        }
    }
}
