/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.common.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


/**
 *
 * @author SEMEN
 */
@Configuration
@Import({ThymeleafConfig.class, DataConfig.class, SecurityConfiguration.class})
//@ComponentScan("com.common.service.*")
public class AppConfig {


}
