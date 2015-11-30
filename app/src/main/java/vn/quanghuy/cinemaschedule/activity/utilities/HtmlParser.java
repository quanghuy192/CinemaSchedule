package vn.quanghuy.cinemaschedule.activity.utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import vn.quanghuy.cinemaschedule.activity.R;
import vn.quanghuy.cinemaschedule.activity.activity.MainActivity;
import vn.quanghuy.cinemaschedule.activity.bean.BaseObject;
import vn.quanghuy.cinemaschedule.activity.bean.Cinema;
import vn.quanghuy.cinemaschedule.activity.bean.Movie;

public class HtmlParser {

    // Link source
    private static final String CURRENT_MOVIE_URL = "http://lichphim.vn/fbapp/lichchieu";
    private static final String NEW_MOVIE_URL = "http://lichphim.vn/fbapp/sapchieu";
    private static final String CINEMA_URL = "http://lichphim.vn/fbapp/rapchieu";

    // to know the tab is selected
    private short CURRENT_MOVIE_ID = 0;
    private short NEW_MOVIE_ID = 1;

    // current index of tab
    private static int idCurrentTab;

    private Document document;
    private String url;

    // data list
    private List<BaseObject> movieList;
    private List<BaseObject> cinemaList;

    private Context context;

    public HtmlParser(Context context) {
        super();
        this.context = context;
        // Get default url to
        // load data current movie
        setUrlSource(CURRENT_MOVIE_URL);

    }

    void setUrlSource(String url) {
        this.url = url;
        try {
            Log.i("URL", url);
            document = Jsoup.connect(url).userAgent("Mozilla").get();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @return movie list get from link source
     */
    public List<BaseObject> getDataList() {
        movieList = new ArrayList<>();
        cinemaList = new ArrayList<>();
        BaseObject object = null;
        idCurrentTab = MainActivity.getIdTabSelected();
        if (idCurrentTab == CURRENT_MOVIE_ID) {
            setUrlSource(CURRENT_MOVIE_URL);
        } else if (idCurrentTab == NEW_MOVIE_ID) {
            setUrlSource(NEW_MOVIE_URL);
        } else {
            setUrlSource(CINEMA_URL);
            setDataCinema();
            return cinemaList;
        }

        Elements elementItems = document.select("div[class=img_item_phim]");

        // Test by logcat
        Log.i("SIZE", "" + elementItems.size());
        for (Element e : elementItems) {
            object = new Movie();
            Elements items = e.select("a");
            for (Element e1 : items) {
                String detailLink = e1.attr("href");
                String titleContent = e1.attr("title");
                Elements imgSource = e1.select("img");
                Log.i("img", imgSource.attr("src"));
                Bitmap bitmap = null;
                try {
                    // Create Bitmap icon from url source
                    bitmap = BitmapFactory.decodeStream(new URL(imgSource.attr("src")).openStream());

                } catch (MalformedURLException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                } catch (IOException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }
                object.setIcon(bitmap);

                ((Movie) object).setLinkDetail(detailLink);
                solveTitleContent(titleContent,
                        (Movie) object);
            }
            Log.i("ToString",
                    object.toString());

            movieList.add(
                    object);
        }
        return movieList;
    }

    // Hard code data
    private void setDataCinema() {
        cinemaList = new ArrayList<>();
        cinemaList.add(new Cinema("TT CHIẾU PHIM QUỐC GIA", "87 Láng Hạ, Q.Ba Đình, Hà Nội",
                "https://www.google.com/maps/preview?q=21.016783,105.815613", "(04) 35148647", R.drawable.quocgia, context));
        cinemaList.add(new Cinema("CGV VINCOM CENTER BÀ TRIỆU", "Tầng 6, Vincom City Towers, 191 Bà Triệu, Q.Hai Bà Trưng, HN",
                "https://www.google.com/maps/preview?q=21.011756,105.849214", "(04) 3974 3333", R.drawable.cgv, context));
        cinemaList.add(new Cinema("PLATINUM CINEPLEX THE GARDEN", "Tầng 4, Trung tâm thương mại The Garden, HN",
                "https://www.google.com/maps/preview?q=21.015396,105.777816", "(04) 37878 555", R.drawable.platinum, context));
        cinemaList.add(new Cinema("THÁNG 8", "45 Hàng Bài, Hà Nội",
                "https://www.google.com/maps/preview?q=21.021651,105.852585", "(04) 38253911", R.drawable.eight, context));
        cinemaList.add(new Cinema("LOTTE KEANGNAM LAND MARK HANOI", "Lô E, Phạm Hùng, Từ Liêm, Hà Nội",
                "https://www.google.com/maps/preview?q=21.017221,105.783988", "(04) 3837 8035", R.drawable.lotte, context));
        cinemaList.add(new Cinema("CGV MIPEC TOWER (MEGASTAR)", "229 Tây Sơn, Q.Đống Đa, Hà Nội",
                "https://www.google.com/maps/preview?q=21.006938,105.822991", "(04)6252 3333", R.drawable.cgv, context));
        cinemaList.add(new Cinema("KIM ĐỒNG", "19 Hàng Bài, Q.Hoàn Kiếm, Hà Nội",
                "https://www.google.com/maps/preview?q=21.024712,105.85291", "(04) 3939 3474", R.drawable.kimdong, context));
        cinemaList.add(new Cinema("PLATINUM VINCOM LONG BIÊN", "Phúc Lợi, Q.Long Biên, Hà Nội",
                "https://www.google.com/maps/preview?q=21.051101,105.916436", "", R.drawable.platinum, context));
        cinemaList.add(new Cinema("LOTTE CINEMA HÀ ĐÔNG", "Tầng 5 Mê Linh Plaza. Tô Hiệu. Hà Đông. Hà Nội",
                "https://www.google.com/maps/preview?q=20.966088,105.771632", "(043) 355 8011", R.drawable.lotte2, context));
        cinemaList.add(new Cinema("PLATINUM ROYAL CITY", "Tầng B2 – Vincom MegaMall Royal City 72 Nguyễn Trãi, Thanh Xuân, Hà Nội",
                "https://www.google.com/maps/preview?q=21.007759,105.814106", "", R.drawable.platinum, context));
        cinemaList.add(new Cinema("PLATINUM TIMES CITY", "Tầng B1 (Tòa T10) – Vincom Mega Mall Times City",
                "https://www.google.com/maps/preview?q=20.999146,105.868782", "04.3975.1166", R.drawable.platinum, context));
        cinemaList.add(new Cinema("DÂN CHỦ CINEMA", "211 Khâm Thiên, Thổ Quan, Q. Đống Đa, Hà Nội",
                "https://www.google.com/maps/preview?q=21.016783,105.815613", "(04)8516702", R.drawable.danchu, context));
        cinemaList.add(new Cinema("CGV HỒ GƯƠM PLAZA", "Tầng 3, TTTM Hồ Gươm Plaza, 110 Trần Phú, Phường Mỗ Lao, Quận Hà Đông, Hà Nội",
                "https://www.google.com/maps/preview?q=20.9777193,105.7846927", "043 311 7000", R.drawable.cgv, context));
        cinemaList.add(new Cinema("CGV AEON LONG BIÊN", "Tầng 4, Trung tâm thương mại Aeon Long Biên - 27 Cổ Linh, quận Long Biên, TP.Hà Nội",
                "https://www.google.com/maps/preview?q=21.026976,%20105.898453", "04 3269 3000", R.drawable.cgv, context));
        cinemaList.add(new Cinema("FAFIM CINEMA", "19, Nguyễn Trãi, P.Khương Trung, Q. Thanh Xuân, Hà Nội",
                "https://www.google.com/maps/preview?q=21.002044,%20105.820746", "04 66640145", R.drawable.fafim, context));
        cinemaList.add(new Cinema("CGV VINCOM NGUYỄN CHÍ THANH", "Số 54A Nguyễn Chí Thanh, Phường Láng Thượng, Quận Đống Đa, Hà Nội",
                "https://www.google.com/maps/preview?q=21.023574,%20105.809104", "84 4 3 835 3333", R.drawable.cgv, context));
    }

    /**
     * @param titleContent: get titleContent from title in html link
     * @param movie:        set atttribute for movie object
     */
    private void solveTitleContent(String titleContent, Movie movie) {
        // TODO Auto-generated method stub

        // Create document from string
        Document doc = Jsoup.parse(titleContent);

        // Filter span tag
        Elements elements = doc.select("span");
        int lenght = elements.size();

        if (idCurrentTab == CURRENT_MOVIE_ID) {
            for (int i = 0; i < lenght; i++) {
                movie.setTitle(elements.get(0).text());
                movie.setType(elements.get(2).text());
                movie.setTime(elements.get(4).text());
                movie.setDirector(elements.get(6).text());
                movie.setActors(elements.get(8).text());
                movie.setImdbPoint(elements.get(10).text());
                movie.setDayStart(convertDay(elements.get(12).text()));
                movie.setContent(elements.get(13).text());
            }
        } else {
            for (int i = 0; i < lenght; i++) {
                movie.setTitle(elements.get(0).text());
                movie.setType(elements.get(2).text());
                movie.setTime(elements.get(4).text());
                movie.setDirector(elements.get(6).text());
                movie.setActors(elements.get(8).text());
                movie.setDayStart(convertDay(elements.get(10).text()));
                movie.setContent(elements.get(11).text());
            }
        }

    }

    // Convert day type yyyy-mm-dd to dd/mm/yyyy
    private String convertDay(String day) {
        StringBuilder builder = new StringBuilder();
        String[] partOfString = day.split("-");
        int size = partOfString.length;
        for (int i = size - 1; i >= 0; i--) {
            builder.append(partOfString[i]);
            builder.append("/");
        }
        String dayAfterConvert = new String(builder);
        dayAfterConvert = dayAfterConvert.substring(0, dayAfterConvert.length() - 1);
        return dayAfterConvert;
    }

}
