package com.judy.message.common.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SingleResponse extends BaseResponse {

    private Object data;

    public SingleResponse(String resultCode, String resultMessage) {
        super(resultCode, resultMessage);
    }

    @Builder
    public SingleResponse(String resultCode, String resultMessage, Object data) {
        super(resultCode, resultMessage);
        this.data = data;
    }

}
