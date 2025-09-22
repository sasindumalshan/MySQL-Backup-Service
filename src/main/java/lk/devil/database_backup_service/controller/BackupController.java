package lk.devil.database_backup_service.controller;

import lk.devil.database_backup_service.service.DBService;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sasindu Malshan
 * @project database-backup-service
 * @date 9/22/2025
 */
@RestController
@RequestMapping("api/db/backup")
public class BackupController {

    private final DBService dbService;

    public BackupController(DBService dbService) {
        this.dbService = dbService;
    }

    @PostMapping("local")
    public ResponseEntity dbBackupToLocal() {
        String locationInStored = dbService.backupLocalMySQL();
        if (locationInStored == null) return ResponseEntity.unprocessableEntity().build();
        return ResponseEntity.ok().body(locationInStored);
    }

}
