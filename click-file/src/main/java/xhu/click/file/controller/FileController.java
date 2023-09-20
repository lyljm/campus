package xhu.click.file.controller;

import io.minio.GetObjectResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xhu.click.common.entity.pojo.ResultVO;
import xhu.click.file.constants.FilePathConstants;
import xhu.click.file.service.MinioService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

@Api(tags = "文件")
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private MinioService minioService;


    // 上传，上传成功会返回文件名
    @ApiOperation("文件上传")
    @ApiImplicitParam(name = "file",value = "文件，multipartFile")
    @PostMapping
    public ResultVO upload(@NotNull MultipartFile file) throws Exception {
        // 上传
        String url = minioService.putObject(FilePathConstants.FILE_PATH,file);
        // 返回文件名
        return ResultVO.ok(url);
    }

    // 根据文件名下载文件
    @ApiOperation("下载文件")
    @ApiImplicitParam(name = "fileName",value = "文件路径+文件名，例:click/avatar/asdfsdfsdcd.jpg")
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
    @ApiOperation("删除文件")
    @ApiImplicitParam(name = "fileName",value = "文件路径+文件名，例:click/avatar/asdfsdfsdcd.jpg")
    @DeleteMapping("{fileName}")
    public String remove(@PathVariable("fileName") String fileName) throws Exception  {
        minioService.removeObject(fileName);
        return "success";
    }
}
