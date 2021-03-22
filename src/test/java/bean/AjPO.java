package bean;

import com.pengjianzhong.copyfield.annotation.CopyField;

/**
 * @author pengjianzhong
 * @date 2021/1/6 11:27
 */
public class AjPO {

    @CopyField(name = "bh")
    String CBh;
    @CopyField(name = "ajbh")
    String CBhAj;

    @CopyField(name = "ah")
    String CAh;

    @CopyField(name = "ajxh" ,convert = "com.pengjianzhong.copyfield.convert.Long2IntegerConvert")
    Long NAjxh;

    public String getCBh() {
        return CBh;
    }

    public void setCBh(String CBh) {
        this.CBh = CBh;
    }

    public String getCBhAj() {
        return CBhAj;
    }

    public void setCBhAj(String CBhAj) {
        this.CBhAj = CBhAj;
    }


    public void setCAh(String CAh) {
        this.CAh = CAh;
    }

    public Long getNAjxh() {
        return NAjxh;
    }

    public void setNAjxh(Long NAjxh) {
        this.NAjxh = NAjxh;
    }

    @Override
    public String toString() {
        return "AjPO{" +
                "CBh='" + CBh + '\'' +
                ", CBhAj='" + CBhAj + '\'' +
                ", CAh='" + CAh + '\'' +
                ", NAjxh=" + NAjxh +
                '}';
    }
}