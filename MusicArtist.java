class MusicArtist extends Artist {

    public MusicArtist(String name) {
        super(name);
    }

    @Override
    public String getType() {
        return "Music Artist";
    }
}
