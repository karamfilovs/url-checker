public enum Locale {
    EN_DE("en_de"),
    DE_DE("de_de"),
    DE_AT("de_at"),
    EN_AT("en_at"),
    EN_DK("en_dk"),
    FR_FR("fr_fr"),
    EN_FR("en_fr"),
    IT_IT("it_it"),
    EN_IT("en_it");

    Locale(String locale) {
        this.locale = locale;
    }

    public String getLocale() {
        return locale;
    }

    private String locale;


}
