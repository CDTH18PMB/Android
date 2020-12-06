package com.hoanglam.congthucnauan;

public class CacBuocLam {
    private String Image;
    private String Step;
    private String Content;

    public CacBuocLam(){
        this.Image = "";
        this.Step = "";
        this.Content = "";
    }

    public CacBuocLam(String img, String step, String content){
        this.Image = img;
        this.Step = step;
        this.Content = content;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getStep() {
        return Step;
    }

    public void setStep(String step) {
        Step = step;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
