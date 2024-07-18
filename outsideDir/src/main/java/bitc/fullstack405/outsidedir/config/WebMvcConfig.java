package bitc.fullstack405.outsidedir.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${resource.gif.location}")
    private String gifLocation;

    @Value("${resource.img.location}")
    private String imgLocation;

    @Value("${resource.upload.location}")
    private String uploadLocation;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        외부 폴더를 원하는 만큼 계속 추가 가능

//        addResourceHandler(경로): 스프링 프레임워크 외부에 있는 폴더를 스프링 프레임워크 내부에 있는 폴더인 것으로 인식 시킴
//        매개변수로 내부에서 사용할 경로를 받아옴

//        addResourceLocations(경로): 실제 디스크의 외부 폴더 경로를 입력
//        mac, linux는 'file://경로명', windows는 'file:///경로명' 형태로 사용
//        registry.addResourceHandler("/img/**").addResourceLocations("file:///C:/fullstack405/움직이는/");

        String path1 = "file:///" + gifLocation;
        String path2 = "file:///" + imgLocation;
        String path3 = "file:///" + uploadLocation;

        registry.addResourceHandler("/img2/**").addResourceLocations(path1, path2, path3);
//        registry.addResourceHandler("/img2/**").addResourceLocations("file:///C:/fullstack405/moving/");
    }
}
