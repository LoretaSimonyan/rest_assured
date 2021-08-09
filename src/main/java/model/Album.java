package model;
public class Album {
        private int albumId;
        private int id;
        private String title;
        private String url;
        private String thumbnailUrl;

    public void setAlbumId(int albumId){
        this.albumId = albumId;
    }

    public int getAlbumId(){
        return this.albumId;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(String title){
        return this.title;
    }
}
