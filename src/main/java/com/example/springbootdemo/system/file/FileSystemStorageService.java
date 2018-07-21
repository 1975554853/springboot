package com.example.springbootdemo.system.file;
import com.example.springbootdemo.system.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author fly
 * 文件上传服务类
 */
@Service
public class FileSystemStorageService {

    private final Path rootLocation;


    /**
     * 读取文件上传配置文件，将路径转为对象
     */
    @Autowired
    public FileSystemStorageService(FileUploadProperties fileUploadProperties) {
        this.rootLocation=Paths.get(fileUploadProperties.getStaticLocation());
    }

    /**
     * 文件上传类,返回一个全新的路径
     */
    public String store(MultipartFile multipartFile){

        if (multipartFile == null || multipartFile.isEmpty()){
            throw new CustomException(101,"上传文件为空");
        }
        try {

            /**
             * 自定义文件上传文件夹，为了方便管理，推荐使用上传时间当做文件目录;
             * 确保文件名称不会重复，推荐使用UUID或者上传时间毫秒数重命名文件名称
             */
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String data = simpleDateFormat.format(new Date());
            Integer suffix = multipartFile.getOriginalFilename().lastIndexOf(".");
            String suffixName = multipartFile.getOriginalFilename().substring(suffix);

            /**
             * 使用UUID，重新生成文件名
             */
            suffixName = UUID.randomUUID().toString().replace("-","").toLowerCase() + suffixName;

            /**
             * 目录如果不存在，先创建目录
             */
            if(!Files.exists(this.rootLocation.resolve(data))){
                Files.createDirectory(this.rootLocation.resolve(data));
            }

            /**
             * 将文件存到本地，
             * this.rootLocation.resolve(multipartFile.getOriginalFilename())的意思是：
             * 将文件名称自动追加到上传路径后边，比如上传文件叫 1.txt , resolve之后，
             * 上传路径就变成了 E:/image/2018-06-29/32c3f5fbac5744f68ec7efa1f9940293.txt
             */
            Files.copy(multipartFile.getInputStream(),this.rootLocation
                    .resolve(data)
                    .resolve(suffixName));

            return data + File.separator + suffixName;

        } catch (IOException e) {

            throw new CustomException(102,multipartFile.getOriginalFilename()+"复制出错");

        }
    }

    /**
     * 文件下载
     * @param fileName
     * @return
     */
    public Resource loadAsResource(String fileName){
        /**
         * 得到文件路径对象
         */
        Path file = load(fileName);
        try {
            // 通过资源加载文件对象
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()){
                return resource;
            }else{
                throw new CustomException(103,"请求资源不存在");
            }
        } catch (MalformedURLException e) {
            throw new CustomException(103,"请求资源不存在");
        }
    }

    /**
     * 得到文件路径path对象
     * @param fileName
     * @return
     */
    private Path load(String fileName) {
        return rootLocation.resolve(fileName);
    }

}