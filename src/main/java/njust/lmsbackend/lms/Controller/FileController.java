package njust.lmsbackend.lms.Controller;

import njust.lmsbackend.lms.POJO.*;
import njust.lmsbackend.lms.Result.Result;
import njust.lmsbackend.lms.Result.ResultFactory;
import njust.lmsbackend.lms.Service.FileService;
import njust.lmsbackend.lms.DAO.UploadFileResponseDAO;
import njust.lmsbackend.lms.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    @Autowired
    UserService userService;
    @Autowired
    private FileService fileService;

    @ResponseBody
    @PostMapping("/api/admin/uploadFile")
    public Result uploadFile(@RequestParam("file") MultipartFile file, @RequestParam String studentId) {
        String fileName = fileService.storeFile(file);

        AppointmentPOJO appointmentPOJOList = userService.queryAppointmentById(studentId);
        ParticipationPOJO participationPOJO = userService.findExpIdByStudentId(appointmentPOJOList.getStudentId());
        String expId = participationPOJO.getExp_id();

        String fileDownloadUri = "E:\\report\\" + fileName;
        fileService.SaveParticipation(studentId, expId, fileDownloadUri);
        logger.info("上传文件成功");
        return ResultFactory.buildSuccessResult_p("上传文件成功", new FilePropertiesPOJO(fileName, fileDownloadUri,
                file.getContentType(), file.getSize(), fileService.fileDir()));
    }

    /*@PostMapping("/api/admin/uploadMultipleFiles")
    public List<UploadFileResponseDAO> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.stream(files)
                .map(this::uploadFile)
                .collect(Collectors.toList());
    }*/

    /*@GetMapping("/api/admin/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }*/
}
