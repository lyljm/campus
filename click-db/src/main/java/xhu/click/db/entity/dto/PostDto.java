package xhu.click.db.entity.dto;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * post表
 * </p>
 *
 * @author eifying
 * @since 2023-09-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Post对象", description="post表")
public class PostDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "主题id")
    private Long subjectId;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "文件")
    private MultipartFile[] files;

    @ApiModelProperty(value = "post状态，0草稿，1未审核，2已发布")
    private Integer status;
}
