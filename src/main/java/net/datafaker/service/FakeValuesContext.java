package net.datafaker.service;

import net.datafaker.internal.helper.SingletonLocale;

import java.net.URL;
import java.util.Locale;
import java.util.Objects;

class FakeValuesContext {
    private final SingletonLocale sLocale;
    private final String filename;
    private String path;
    private final URL url;

    private FakeValuesContext(Locale locale) {
        this(locale, getFilename(locale), getFilename(locale), null);
    }

    private FakeValuesContext(Locale locale, URL url) {
        this(locale, getFilename(locale), null, url);
    }

    private FakeValuesContext(Locale locale, String filename, String path) {
        this(locale, filename, path, null);
    }

    private FakeValuesContext(Locale locale, String filename, String path, URL url) {
        this.sLocale = SingletonLocale.get(locale);
        this.filename = filename;
        this.path = path;
        this.url = url;
    }

    public static FakeValuesContext of(Locale locale) {
        return new FakeValuesContext(locale);
    }

    public static FakeValuesContext of(Locale locale, URL url) {
        return new FakeValuesContext(locale, url);
    }

    public static FakeValuesContext of(Locale locale, String filename, String path) {
        return new FakeValuesContext(locale, filename, path);
    }

    public static FakeValuesContext of(Locale locale, String filename, String path, URL url) {
        return new FakeValuesContext(locale, filename, path, url);
    }

    private static String getFilename(Locale locale) {
        final String lang = language(locale);
        if ("".equals(locale.getCountry())) {
            return lang;
        }
        return lang + "-" + locale.getCountry();
    }

    /**
     * If you new up a locale with "he", it gets converted to "iw" which is old.
     * This addresses that unfortunate condition.
     */
    private static String language(Locale l) {
        if (l.getLanguage().equals("iw")) {
            return "he";
        }
        return l.getLanguage();
    }

    public Locale getLocale() {
        return sLocale.getLocale();
    }

    public String getFilename() {
        return filename;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public URL getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FakeValuesContext that)) return false;
        return sLocale == that.sLocale && Objects.equals(filename, that.filename) && Objects.equals(path, that.path) && Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sLocale, filename, path, url);
    }

    @Override
    public String toString() {
        return "FakeValuesContext{" +
            "sLocale=" + sLocale +
            ", filename='" + filename + '\'' +
            ", path='" + path + '\'' +
            ", url=" + url +
            '}';
    }
}
