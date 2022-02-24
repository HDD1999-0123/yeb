package com.hdd.server.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @author hedd
 * @create 2021/6/16 9:30
 * @Desc TODO
 */
/*
public class FastDFSUtils {
    private static Logger logger = LoggerFactory.getLogger(FastDFSUtils.class);
    /**
        初始化客户端
        ClientGlobal.init 读取配置文件，并初始化对应的属性
 */


/*
    static {
        try {
            String filePath = new ClassPathResource("fdfs_client_conf").getFile().getAbsolutePath();
            ClientGlobal.init();
        } catch (IOException e) {
            logger.error("初始化FastDFS失败==>{}",e;
        }
    }
    生产storage客户端
    private static StorageClient getStorageClient(){
        TrackerServer trackerServer = getTrackerServer();
        StorageClient storageClient = new StorageClient(trackerServer,null);
        return storageClient;
    }
    生成tracker服务器
    private static TrackerServer getTrackerServer(){
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getTrackerServer();
        return trackerServer;
    }

    public static String[] upload(MultipartFile file){
        String name = file.getOriginalFilename();
        logger.info("文件名",name);
        StorageClient storageClient = null;
        String[] uploadResults = null;
        try{
            //获取storage客户端
            storageClient = getStorageClient();
            //上传
            uploadResults = storageClient.upload_file(file.getBytes(),name.substring(name.lastIndexOf(".")+1,null);
        }catch (IOException e){
            e.printStackTrace();

        }
        if(null == uploadResults){
            logger.error("上传失败");
        }
        return uploadResults;
    }
    获取文件信息
    public static FileInfo getFileInfo(String groupName,String remoteFileName){
        StorageClient storageClient = null;
        try{
            storageClient = getStorageClient();
            FileInfo fileInfo = storageClient.get_file_info(groupName,remoteFileName);
            return fileInfo;
        }catch(IOException e){
        }
        return null;
    }
    下载文件
    public static InputStream downFile(String groupName,String remoteFIleName){
        StorageClient storageClient = null;
        try{
            storageClient = getStorageClient();
            byte[] fileByte = storageClient.download_file(groupName,remoteFileName);
            InputStream inputStream = new ByteArrayInputStream(fileByte);
            return inputStream;
        }catch(IOException e){
        }
        return null;
    }
    删除文件
    public static void deleteFile(String groupName,String remoteFileName){
        StorageClient storageClient = null;
        try{
            storageClient = getStorageClient();
            storageClient.delete_file(groupName,remoteFileName);
        }catch(IOException e){
        }
        return null;
    }
    获取文件路径
    public static String getTrackerUrl(){
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = null;
        StorageServer storeStorage = null;
        try{
            trackerServer = trackerClient.getTrackerServer();
            storeStorage = trackerClient.getStoreStorage(trackerServer);
        }
        return "http://"+storeStorage.getInetSocketAddress().getHostString()+":8888/";
    }
}
*/