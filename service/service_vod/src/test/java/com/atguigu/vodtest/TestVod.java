package com.atguigu.vodtest;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import java.util.List;

public class TestVod {
  private static final String accessKeyId = "keyid";
  private static final String accessKeySecret = "keysecret";
  public static final String videoId = "6641a080594f47bcafaabe54e9268f28";

  public static void main(String[] args) throws ClientException {
//    getPlayAuth();
//    printUrlAndTitle();
    uploadVideo();
  }

  private static void uploadVideo() {
    String fileName = "E:\\6 - What If I Want to Move Faster.mp4";
    String title = "0607 - Uploaded video";
    UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
    request.setPartSize(2 * 1024 * 1024L);
    request.setTaskNum(1);
    UploadVideoImpl uploader = new UploadVideoImpl();
    UploadVideoResponse response = uploader.uploadVideo(request);
    System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
    if (response.isSuccess()) {
      System.out.print("VideoId=" + response.getVideoId() + "\n");
    } else {
      /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
      System.out.print("VideoId=" + response.getVideoId() + "\n");
      System.out.print("ErrorCode=" + response.getCode() + "\n");
      System.out.print("ErrorMessage=" + response.getMessage() + "\n");
    }
  }

  private static void getPlayAuth() throws ClientException {
    DefaultAcsClient client = InitObject.initVodClient(accessKeyId, accessKeySecret);

    GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
    request.setVideoId(videoId);

    GetVideoPlayAuthResponse response = client.getAcsResponse(request);
    System.out.println("PlayAuth: " + response.getPlayAuth());
  }

  private static void printUrlAndTitle() throws ClientException {
    // get video address based on video id
    DefaultAcsClient client = InitObject.initVodClient(accessKeyId, accessKeySecret);

    GetPlayInfoRequest request = new GetPlayInfoRequest();
    request.setVideoId(videoId);
    GetPlayInfoResponse response = client.getAcsResponse(request);

    List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
    for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
      System.out.println("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
    }
    System.out.println("VideoBase.Title = " + response.getVideoBase().getTitle());
  }
}
