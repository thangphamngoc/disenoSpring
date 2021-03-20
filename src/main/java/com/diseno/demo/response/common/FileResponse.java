package com.diseno.demo.response.common;

import com.diseno.demo.response.base.IdResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * date 2021-03-19 09:02
 *
 * @author Phạm Ngọc Thắng
 */

@Getter
@Setter
public class FileResponse extends IdResponse {

    private String name;

    private String url;
}
