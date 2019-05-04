package info.nguyentai.api.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import info.nguyentai.api.Constants.VodStatus;
import info.nguyentai.api.entity.AddChannelRequestModel;
import info.nguyentai.api.entity.Stream;
import info.nguyentai.api.repository.StreamerRepository;


@RestController
public class StreamerAPIController {

	private static final Logger logger = LoggerFactory.getLogger(StreamerAPIController.class);
	@Value("${streamer.available:14,15}")
	private String[] availableStreamers; 
	@Value("${origin.url:http://172.17.1.215:1936}")
	private String originUrl; 
	@Autowired
	private StreamerRepository streamerRepository;
	
	
	@GetMapping("/ping")
	public String ping() throws JsonProcessingException {
		return "{\"type\": \"pong\"}";
	}

	@GetMapping(name = "/streams", produces = "application/json")
	public List<Stream> streams() throws IOException {
		
		List<Stream> listApiStreamers = streamerRepository.findAll();
		logger.info("=== List streams ===");
		setStreamAvailability(listApiStreamers);
		for (Stream stream : listApiStreamers) {
			logger.info(stream.toString());
		}
		logger.info("\n\n");
		return listApiStreamers;
	}
	
	@PostMapping(value = "/streams/{streamId}")
	public ResponseEntity<String> addStream(@PathVariable String streamId, @RequestBody AddChannelRequestModel requestModel) {
		if(streamerRepository.findById(streamId).isPresent()) {
			logger.error("Add stream fail: Exists stream with id: " + streamId);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (!tossCoin(80)) {
			logger.error("Add stream fail: unlucky coin toss, id " + streamId);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		try {
			Stream newStream = new Stream();
			newStream.setId(streamId);
			newStream.setSource(requestModel.getUrl());
			newStream.setLastReceived(new Date());
			newStream.setRepresentations("");
			newStream.setStatus(VodStatus.UPLOADING.getRequestCode());
			newStream.setStarted(new Date());
			newStream.setMaxChunkCount(10);
			newStream.setPlaylistChunkCount(5);
			streamerRepository.save(newStream);
			logger.info("Added stream success, id: " + streamId);
			return new ResponseEntity<>(HttpStatus.CREATED);
			
		} catch (Exception e) {
			logger.error("Add stream fail, id " + streamId, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/streams/{streamId}")
	public ResponseEntity<String> removeStream(@PathVariable String streamId) {
		if(!streamerRepository.findById(streamId).isPresent()) {
			logger.error("Delete stream fail, no content found with id: " + streamId);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (!tossCoin(80)) {
			logger.error("Delete stream fail: foced to fail by ADMIN, id " + streamId);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		try {
			Stream tobeDeletedStream = streamerRepository.findById(streamId).get();
			streamerRepository.delete(tobeDeletedStream);
			logger.info("Deleted stream success, id: " + streamId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		} catch (Exception e) {
			logger.error("Delete stream fail:, id " + streamId, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private void setStreamAvailability(List<Stream> streamList) {
		for (Stream stream : streamList) {
			
			// 2. Update status & last received date
			if (tossCoin(90)) {
				// 2.1. Update status
				stream.setStatus(VodStatus.UPLOADING.getRequestCode());
				// 2.2. Update last received date
				if (tossCoin(70)) {
					stream.setLastReceived(new Date());
				} else {
					Calendar cal = Calendar.getInstance();
					cal.set(2000, 0, 1);
					stream.setLastReceived(cal.getTime());
				}
				streamerRepository.save(stream);
			} else {
				stream.setStatus(VodStatus.DELETED.getRequestCode());
				streamerRepository.save(stream);
			}
			
		}
	}
	
	/**
	 * @param successPercentage
	 * @return
	 */
	private boolean tossCoin(int successPercentage) {
		Random rand = new Random();
		int tossResult = rand.nextInt(100) + 1;
		if (tossResult <= successPercentage) {
			return true;
		}
		return false;
	}

}
