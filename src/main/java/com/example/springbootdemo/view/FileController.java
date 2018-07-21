package com.example.springbootdemo.view;

import com.example.springbootdemo.system.file.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/demo")

public class FileController {

    @Autowired
    FileSystemStorageService fileSystemStorageService;
    /**
     * 文件上传的接口,与SSM没有区别,需要注意的是
     * 页面上的from表单必须提供 enctype="multipart/form-data" 属性
     * @param multipartFile 上传文件字节流对象
     * @return
     */


    @PostMapping("/upload")
    public Object upload(@RequestParam("file") MultipartFile multipartFile){
        String path = fileSystemStorageService.store(multipartFile);
        return path;
    }


    /**
     * 下载文件的接口,与SSM也没有区别,需要注意的是,
     * 这既是一个下载接口,也是一个展示图片的接口(只适用于图片与视频),
     * 如果你的请求地址为 : <a  ="/demo/download?filename=2018-06-30/dd9cca9215154be0a5ce4045f74a1f97.PNG">下载</a>,
     * 那么就会提供下载功能, 如果地址为 : <img src="/demo/download?filename=2018-06-30/dd9cca9215154be0a5ce4045f74a1f97.PNG">,
     * 他就会自动展示图片(实际中应用非常广泛,这样做可以避免暴露服务器真实地址),
     * 当然使用传统的通过虚拟路径的方式也可以访问到服务器图片,
     * 但是因为springboot内置tomact,所以我们需要在配置文件中配置虚拟路径,
     * 并且在拦截器中提供相应配置 见 : HandleConfig
     * @param filename
     * @return
     */
    @GetMapping("/download")
    public ResponseEntity<Resource> download(String filename){
        Resource resource = fileSystemStorageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+filename+"\"")
                .body(resource);
    }
}
