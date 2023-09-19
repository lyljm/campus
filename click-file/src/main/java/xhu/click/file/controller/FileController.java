package xhu.click.file.controller;

import io.minio.GetObjectResponse;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xhu.click.common.entity.pojo.ResultVO;
import xhu.click.file.config.FilePath;
import xhu.click.file.config.MinioConfig;
import xhu.click.file.service.MinioService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private MinioService minioService;

    // 上传，上传成功会返回文件名
    @PostMapping
    public ResultVO upload(@NotNull MultipartFile file) throws Exception {
        // 上传
        String url = minioService.putObject(FilePath.FILE_PATH,file);
        // 返回文件名
        return ResultVO.ok(url);
    }

    // 根据文件名下载文件
    @GetMapping("{fileName}")
    public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable("fileName") String fileName) throws Exception  {
        // 设置响应类型
        response.setCharacterEncoding(request.getCharacterEncoding());
//        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        // 获取文件流
        GetObjectResponse objectResponse = minioService.getObject(fileName);
        // 将文件流输出到响应流
        IOUtils.copy(objectResponse, response.getOutputStream());
        // 结束
        response.flushBuffer();
        objectResponse.close();
    }

    // 根据文件名删除文件
    @DeleteMapping("{fileName}")
    public String remove(@PathVariable("fileName") String fileName) throws Exception  {
        minioService.removeObject(fileName);
        return "success";
    }
}
