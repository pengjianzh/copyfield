package bean;

import com.pengjianzhong.copyfield.annotation.CopyField;

/**
 * @author pengjianzhong
 * @date 2021/1/6 11:25
 */
public class AjVO {

    @CopyField(name = "CBh")
    String bh;

    @CopyField(name = "CBhAj")
    String ajbh;

    @CopyField(name = "CAh")
    String ah;

    @CopyField(name = "NAjxh", convert = "com.pengjianzhong.copyfield.convert.Long2IntegerConvert")
    Integer ajxh;

    public String getBh() {
        return bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public String getAjbh() {
        return ajbh;
    }

    public void setAjbh(String ajbh) {
        this.ajbh = ajbh;
    }

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    public Integer getAjxh() {
        return ajxh;
    }

    public void setAjxh(Integer ajxh) {
        this.ajxh = ajxh;
    }

    @Override
    public String toString() {
        return "AjVO{" +
                "bh='" + bh + '\'' +
                ", ajbh='" + ajbh + '\'' +
                ", ah='" + ah + '\'' +
                ", ajxh=" + ajxh +
                '}';
    }
}