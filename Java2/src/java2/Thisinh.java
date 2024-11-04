
package java2;

//Ham khoi tao
public class Thisinh {
    private int soBD;
    private String hoTen;
    private String gT;
    private String nganhH;
    private double tongD;
   
    //contructor ngầm định
    public  Thisinh(){
    }
    //Contructor tường minh
    public Thisinh(int soBD, String hoTen, String gT, String nganhH, double tongD){
        this.soBD = soBD;
        this.hoTen = hoTen;
        this.gT = gT;
        this.nganhH = nganhH;
        this.tongD = tongD;
    }
    //cac getter/setter cho cac attribute
    
    public int getSoBD(){
        return soBD;
    }
    public void setSoBD(int soBD){
        this.soBD = soBD;
    }
    public String getHoTen(){
        return hoTen;
    }
    public void setHoTen(String hoTen){
        this.hoTen = hoTen;
    }
    public String getGT(){
        return gT;
    }
    public void setGT(String gT){
        this.gT = gT;
    }
    public String getNganhH(){
        return nganhH;
    }
    public void setNganhH(String nganhH){
        this.nganhH = nganhH;
    }
    public double getTongD(){
        return tongD;
    }
    public void setTongD(double tongD ){
        this.tongD = tongD;
    }
    
    public String Hocbong(){
        if (tongD >= 29)
            return "HB";
        else{
            return "";
        }
    }
    
  
}

