package top.sxh427.mall.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Verify implements Serializable {
    private Integer id; //管理员或用户id
    private String phone;
    private Integer status;
}

