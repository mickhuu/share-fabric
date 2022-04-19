package cn.edu.gxmzu.ai.mickhu.controller;

import cn.edu.gxmzu.ai.mickhu.service.impl.CryptoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mickhu
 * @describe
 * @date 2022-04-09 21:26
 */
@RestController
public class CryptoController {

    @Autowired
    CryptoServiceImpl cryptoService;

}
