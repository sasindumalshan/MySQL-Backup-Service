package lk.devil.database_backup_service.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Sasindu Malshan
 * @project database-backup-service
 * @date 9/22/2025
 */
@Service
public class DBService {

    private final GDriveService gDriveService;

    @Value("${db.host}")
    String host;
    @Value("${db.port}")
    String port;
    @Value("${db.user}")
    String user;
    @Value("${db.pw}")
    String password;
    @Value("${db.db-name}")
    String dbName;
    @Value("${db.backupPath}")
    String backupPath;

    public DBService(GDriveService gDriveService) {
        this.gDriveService = gDriveService;
    }

    public String backupLocalMySQL() {
        try {

            String[] command = new String[]{
                    "mysqldump",
                    "-h", host,
                    "-P", port,
                    "-u" + user,
                    "-p" + password,
                    dbName
            };

            String fileName=getFileName("mysql");

            ProcessBuilder pb = new ProcessBuilder(command);
            pb.redirectOutput(new java.io.File(backupPath+fileName+".sql"));
            Process process = pb.start();
            int exitCode = process.waitFor();
            if (exitCode == 0)
                return backupPath+fileName+".sql";
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getFileName(String db){
        SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_a");
        String formattedDate = format.format(new Date());
        return db+"_"+formattedDate;
    }

    @Scheduled(cron = "${backup.schedule.cron}")
    public void scheduledBackup() {
        try {
            String backupPath = backupLocalMySQL();
            gDriveService.backupToGDrive(backupPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
