package scenes.spectrogram;

import audio.AudioAnalyser;
import processing.core.PApplet;
import ddf.minim.AudioSource;
import ddf.minim.analysis.FFT;

public class Spectrogram {

	private AudioSource audioSource;
	private AudioAnalyser audioAnalyser;
	private FFT spectrum;
	private float[] waveform;
	private int waveformBufferLength;
	private float[] realSpectrum;
	private int spectrumBufferLength;

	public Spectrogram(PApplet applet, AudioSource audioSource) {
		this.audioSource = audioSource;
		this.audioAnalyser = new AudioAnalyser(applet, audioSource);
	}
	
	public void update() {
		waveform = audioAnalyser.getWaveform(audioSource.bufferSize());
		waveformBufferLength = waveform.length;
		
		spectrum = audioAnalyser.getFft();
		spectrum.forward(waveform);
		realSpectrum = spectrum.getSpectrumReal();
		spectrumBufferLength = realSpectrum.length;
	}
}
