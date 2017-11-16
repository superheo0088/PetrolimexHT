package hoclv.petrolimexht.uitls;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import hoclv.petrolimexht.R;

/**
 * Created by SuperHeo
 */
public class Utils {


    public static String convertDateComment(String date) {
        if(date==null){
            return "";
        }
        String formattedTime = "";
        if (!date.equals("")) {
            try {
                int index = date.lastIndexOf(".");
                if(index!=-1){
                    date = date.substring(0, index);
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date d = null;
                try {
                    d = sdf.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date currentDate = new Date();
                long diff = Math.abs(currentDate.getTime() - d.getTime());
                long diffMinutes = diff / (60 * 1000);
                long diffHours = diff / (60 * 60 * 1000);
                long diffDays = diff / (24 * 60 * 60 * 1000);

                if (diffDays > 0) {
                    formattedTime = output.format(d);
                } else if (diffHours > 0) {
                    formattedTime = String.valueOf(diffHours) + " giờ trước";
                } else if (diffMinutes > 0) {
                    formattedTime = String.valueOf(diffMinutes) + " phút trước";
                } else {
                    formattedTime = "Vừa xong";
                }
            } catch (Exception ex) {
                formattedTime = "Vừa xong";
            }
        } else {
            formattedTime = "Vừa xong";
        }

        return formattedTime;
    }

    public static String convertDateAsked(String date) {
        if(date==null){
            return "";
        }
        String formattedTime = "";
        if (!date.equals("")) {
            try {
                int index = date.lastIndexOf(".");
                if(index!=-1){
                    date = date.substring(0, index);
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
                Date d = null;
                try {
                    d = sdf.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                formattedTime = output.format(d);

            } catch (Exception ex) {
                formattedTime="";
            }
        } else {
            formattedTime="";
        }

        return formattedTime;
    }

    public static String convertDateAnswer(String date) {
        if(date==null){
            return "";
        }
        String formattedTime = "";
        if (!date.equals("")) {
            try {
                int index = date.lastIndexOf(".");
                if(index!=-1){
                    date = date.substring(0, index);
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                SimpleDateFormat output = new SimpleDateFormat("HH:mm, dd/MM/yyyy ");
                Date d = null;
                try {
                    d = sdf.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                formattedTime = output.format(d);

            } catch (Exception ex) {
                formattedTime="";
            }
        } else {
            formattedTime="";
        }

        return formattedTime;
    }


    public static String formatDate(String date) {

        return formatDate("yyyy-MM-dd", "dd/MM/yyyy HH:mm", date);
    }

    public static String formatDate(String inputFormat, String outputFormat, String inputDate) {
        try {
            Date parsed;
            SimpleDateFormat dfInput = new SimpleDateFormat(inputFormat, java.util.Locale.getDefault());
            SimpleDateFormat dfOutput = new SimpleDateFormat(outputFormat, java.util.Locale.getDefault());

            parsed = dfInput.parse(inputDate);
            return dfOutput.format(parsed);
        } catch (ParseException e) {
            //LOGE(TAG, "ParseException - dateFormat");
        }
        return "";
    }

    public static String formatDate(long timeDiff) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("UTC")); // Date moblie UTC
        c.add(Calendar.SECOND, (int) timeDiff);// ngay hien tai mb + gio chenh lech ta co gio server
        String songay = String.valueOf(c.getTimeInMillis());    // Chuyen ngay ra millisecond tinh tu nam 1970
        return songay;
    }





    public static int getTimeDiff(String timeServer) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            df.setTimeZone(TimeZone.getTimeZone("UTC"));
            String strCurDateMobile = df.format(new Date());

            Date d1 = df.parse(strCurDateMobile);
            Date d2 = df.parse(timeServer);
            long diff = d2.getTime() - d1.getTime();
            return (int) (diff / 1000);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    public static Boolean isNetworkAvailable(Context context) {
        if (context == null) return true;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    /**
     * Xoa du lieu sharedprefrences
     */
    public static void clearSharedPreferencesData(Activity activity) {
        SharedPreferences shre = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor edit = shre.edit();
        edit.clear();
        edit.commit();
    }


    public static void saveSharedPrefrece(String key, String srtContent, Activity activity) {
        SharedPreferences shre = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor edit = shre.edit();
        edit.putString(key, srtContent);
        edit.commit();
    }

    public static String getSharedPrefrece(String key, Activity activity) {
        SharedPreferences shre = PreferenceManager.getDefaultSharedPreferences(activity);
        String strContent = shre.getString(key, "");
        return strContent;
    }
    public static String getSharedPrefrece(String key, Context context) {
        SharedPreferences shre = PreferenceManager.getDefaultSharedPreferences(context);
        String strContent = shre.getString(key, "");
        return strContent;
    }
    public static void saveSharedPrefreceInt(String key, int intContent, Activity activity) {
        SharedPreferences shre = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor edit = shre.edit();
        edit.putInt(key, intContent);
        edit.commit();
    }

    public static int getSharedPrefreceInt(String key, Activity activity) {
        SharedPreferences shre = PreferenceManager.getDefaultSharedPreferences(activity);
        int intContent = shre.getInt(key,0);
        return intContent;
    }


    public static void removeFile(File rooteFile, String key) {
        File[] files = rooteFile.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isDirectory()) {
                    removeFile(file, key);
                } else {
                    if (!key.equals("")) {
                        if (file.getName().contains(key)) {
                            file.delete();
                        }
                    } else {
                        file.delete();
                    }

                }
            }
        }
    }

    public static void deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory())
            for (File child : fileOrDirectory.listFiles())
                deleteRecursive(child);

        fileOrDirectory.delete();
    }
    public static void hideKeyboard(View v, final Context context) {
        // An ban phim
        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent ev) {
                InputMethodManager input = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                input.hideSoftInputFromWindow(view.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                return false;
            }
        });
    }
    public static void popupErr(Activity activity, int mess){
        AlertDialog.Builder dlgMessage = new AlertDialog.Builder(activity);
        dlgMessage.setTitle(R.string.thong_bao)
                .setMessage(mess)
                .setPositiveButton(R.string.btn_close, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    public static void showSnackBar(Context context, View view, String messager) {
        Snackbar snackbar = Snackbar.make(view, messager, Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(context, R.color.colorWhite));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        else
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
        snackbar.show();
    }

    public static String urlEncode(String urlStr) {
        return Uri.encode(urlStr, Consts.ALLOWED_URI_CHARS);
    }

    public static CharSequence noTrailingwhiteLines(CharSequence text) {
        if (text != null && text.length() > 0) {
            while (text.charAt(text.length() - 1) == '\n') {
                text = text.subSequence(0, text.length() - 1);
            }
            return text;
        } else {
            return "";
        }

    }
}
