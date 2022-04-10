package controller;

import model.PseudoQueueModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import repository.PseudoQueueRepo;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class PseudoQueueController {

    @Autowired
    PseudoQueueRepo pseduoQueueRepo;
    @GetMapping("/queues")
    public ResponseEntity<List<PseudoQueueModel>> getAllPseudoQueue(@RequestParam(required = false) String accountNumber) {
        try {
            List<PseudoQueueModel> pseudoQueueModels = new ArrayList<PseudoQueueModel>();
            if (accountNumber == null)
                pseduoQueueRepo.findAll().forEach(pseudoQueueModels::add);
//            else pseduoQueueRepo.findByAccountNumber(accountNumber).forEach(pseudoQueueModels::add);
            if (pseudoQueueModels.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(pseudoQueueModels, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/queues/{id}")
    public ResponseEntity<PseudoQueueModel> getQueueById(@PathVariable("id") long id) {
        Optional<PseudoQueueModel> queueData = pseduoQueueRepo.findById(id);
        return queueData.map(pseudoQueueModel -> new ResponseEntity<>(pseudoQueueModel, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("/queues")
    public ResponseEntity<PseudoQueueModel> createQueue(@RequestBody PseudoQueueModel pseudoQueueModel) {
        try {
            PseudoQueueModel _queue = pseduoQueueRepo
                    .save(new PseudoQueueModel(pseudoQueueModel.getAccountNumber(), pseudoQueueModel.getType(), pseudoQueueModel.getAmount(),pseudoQueueModel.getCurrency(),pseudoQueueModel.getAccountFrom()));
            return new ResponseEntity<>(_queue, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/queues/{id}")
    public ResponseEntity<PseudoQueueModel> updateQueue(@PathVariable("id") long id, @RequestBody PseudoQueueModel pseudoQueueModel) {
        Optional<PseudoQueueModel> queueData = pseduoQueueRepo.findById(id);
        if (queueData.isPresent()) {
            PseudoQueueModel _queue = queueData.get();
            _queue.setAccountNumber(pseudoQueueModel.getAccountNumber());
            _queue.setType(pseudoQueueModel.getType());
            _queue.setAmount(pseudoQueueModel.getAmount());
            _queue.setCurrency(pseudoQueueModel.getCurrency());
            _queue.setAccountFrom(pseudoQueueModel.getAccountFrom());
            return new ResponseEntity<>(pseduoQueueRepo.save(_queue), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/queues/{id}")
    public ResponseEntity<HttpStatus> deleteQueue(@PathVariable("id") long id) {
        try {
            pseduoQueueRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/queues")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        try {
            pseduoQueueRepo.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
