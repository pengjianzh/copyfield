import bean.AjPO;
import bean.AjVO;
import com.pengjianzhong.copyfield.CopyFieldUtils;
import com.pengjianzhong.copyfield.bean.TypeEnum;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author pengjianzhong
 * @date 2020/12/31 15:16
 */
class CopyTest {

    @org.junit.jupiter.api.Test
    void fun1() {
        AjPO ajPo = new AjPO();
        ajPo.setCAh("xxx案号");
        ajPo.setCBh(UUID.randomUUID().toString());
        ajPo.setCBhAj(UUID.randomUUID().toString());
        ajPo.setNAjxh(123L);

        Map<String, Object> map = new HashMap<>();
        map.put("CBhAj",UUID.randomUUID().toString());
        map.put("CBh",UUID.randomUUID().toString());
        map.put("CAh","xxx案号");
        map.put("NAjxh",123L);
        AjVO ajVO = new AjVO();
        try {
            CopyFieldUtils.copy(ajPo,ajVO, TypeEnum.TARGET);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(map);
        System.out.println(ajPo);
        System.out.println(ajVO);
    }

}