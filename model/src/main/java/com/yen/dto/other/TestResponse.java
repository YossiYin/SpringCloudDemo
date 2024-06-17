package com.yen.dto.other;

import lombok.Data;

import java.util.List;

/**
 * @author Yhx
 * @date 2024/6/17 16:32
 */
@Data
public class TestResponse {
    private int status;
    private List<Message> message;
}
