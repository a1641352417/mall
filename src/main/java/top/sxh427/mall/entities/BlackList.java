package top.sxh427.mall.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlackList implements Serializable {
    private Integer id;
    private String phone;
}
