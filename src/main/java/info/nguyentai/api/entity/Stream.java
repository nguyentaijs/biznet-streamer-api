package info.nguyentai.api.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "stream")
public class Stream implements Serializable {

	private static final long serialVersionUID = -7150255593436609937L;

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "source")
	private String source;

	@Column(name = "receivedBytes")
	private Integer receivedBytes = 0;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = info.nguyentai.api.JsonDateTimeSerializer.class)
	private Date lastReceived;

	@Column(name = "pmtPid")
	private Integer pmtPid = 0;

	@Column
	private Integer pcrPid = 0;

	@Column
	private Integer videoPid = 0;

	@Column
	private Integer audioPid = 0;

	@Column
	private String representations = "";

	@Column
	private String status;

	@Transient
	private String errorString = "";

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = info.nguyentai.api.JsonDateTimeSerializer.class)
	private Date started;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = info.nguyentai.api.JsonDateTimeSerializer.class)
	private Date finished;

	@Column
	private Integer dashTimeDelta = 0;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = info.nguyentai.api.JsonTimeSerializer.class)
	private Date chunkDuration = new Date();

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = info.nguyentai.api.JsonTimeSerializer.class)
	private Date fragmentDuration = new Date();

	@Column
	private Integer maxChunkCount = 0;

	@Column
	private Integer playlistChunkCount = 0;

	public Stream() {
		super();
	}

	public Stream(String id, String source, Integer receivedBytes, Date lastReceived, Integer pmtPid, Integer pcrPid,
			Integer videoPid, Integer audioPid, String representations, String status, String errorString,
			Date started, Date finished, Integer dashTimeDelta, Date chunkDuration, Date fragmentDuration,
			Integer maxChunkCount, Integer playlistChunkCount) {
		super();
		this.id = id;
		this.source = source;
		this.receivedBytes = receivedBytes;
		this.lastReceived = lastReceived;
		this.pmtPid = pmtPid;
		this.pcrPid = pcrPid;
		this.videoPid = videoPid;
		this.audioPid = audioPid;
		this.representations = representations;
		this.status = status;
		this.errorString = errorString;
		this.started = started;
		this.finished = finished;
		this.dashTimeDelta = dashTimeDelta;
		this.chunkDuration = chunkDuration;
		this.fragmentDuration = fragmentDuration;
		this.maxChunkCount = maxChunkCount;
		this.playlistChunkCount = playlistChunkCount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Integer getReceivedBytes() {
		return receivedBytes;
	}

	public void setReceivedBytes(Integer receivedBytes) {
		this.receivedBytes = receivedBytes;
	}

	public Date getLastReceived() {
		return lastReceived;
	}

	public void setLastReceived(Date lastReceived) {
		this.lastReceived = lastReceived;
	}

	public Integer getPmtPid() {
		return pmtPid;
	}

	public void setPmtPid(Integer pmtPid) {
		this.pmtPid = pmtPid;
	}

	public Integer getPcrPid() {
		return pcrPid;
	}

	public void setPcrPid(Integer pcrPid) {
		this.pcrPid = pcrPid;
	}

	public Integer getVideoPid() {
		return videoPid;
	}

	public void setVideoPid(Integer videoPid) {
		this.videoPid = videoPid;
	}

	public Integer getAudioPid() {
		return audioPid;
	}

	public void setAudioPid(Integer audioPid) {
		this.audioPid = audioPid;
	}

	public String getRepresentations() {
		return representations;
	}

	public void setRepresentations(String representations) {
		this.representations = representations;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorString() {
		return errorString;
	}

	public void setErrorString(String errorString) {
		this.errorString = errorString;
	}

	public Date getStarted() {
		return started;
	}

	public void setStarted(Date started) {
		this.started = started;
	}

	public Date getFinished() {
		return finished;
	}

	public void setFinished(Date finished) {
		this.finished = finished;
	}

	public Integer getDashTimeDelta() {
		return dashTimeDelta;
	}

	public void setDashTimeDelta(Integer dashTimeDelta) {
		this.dashTimeDelta = dashTimeDelta;
	}

	public Date getChunkDuration() {
		return chunkDuration;
	}

	public void setChunkDuration(Date chunkDuration) {
		this.chunkDuration = chunkDuration;
	}

	public Date getFragmentDuration() {
		return fragmentDuration;
	}

	public void setFragmentDuration(Date fragmentDuration) {
		this.fragmentDuration = fragmentDuration;
	}

	public Integer getMaxChunkCount() {
		return maxChunkCount;
	}

	public void setMaxChunkCount(Integer maxChunkCount) {
		this.maxChunkCount = maxChunkCount;
	}

	public Integer getPlaylistChunkCount() {
		return playlistChunkCount;
	}

	public void setPlaylistChunkCount(Integer playlistChunkCount) {
		this.playlistChunkCount = playlistChunkCount;
	}

	@Override
	public String toString() {
		return "Stream [id=" + id + ", lastReceived=" + lastReceived + ", status=" + status + "]";
	}

}
