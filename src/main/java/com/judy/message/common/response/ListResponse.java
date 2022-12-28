package com.judy.message.common.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ListResponse extends BaseResponse {

    private List<Object> list;

    public ListResponse(String resultCode, String resultMessage) {
        super(resultCode, resultMessage);
    }

    @Builder
    public ListResponse(String resultCode, String resultMessage, List<Object> list) {
        super(resultCode, resultMessage);
        this.list = list;
    }

}
