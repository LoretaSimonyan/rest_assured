package model;

import lombok.Data;

@Data
public class Geo {
    private String lat;
    private String lang;
    //logbag.xml

    public Geo(){}
    public Geo(GeoBuilder geoBuilder) {
        this.lat = geoBuilder.lat;
        this.lang = geoBuilder.lang;
    }

    public static class GeoBuilder {
        private String lat;
        private String lang;

        public GeoBuilder setLat(String lat) {
            this.lat = lat;
            return this;
        }

        public GeoBuilder setLang(String lang) {
            this.lang = lang;
            return this;
        }

        public Geo build() {
            return new Geo(this);
        }
    }
}
