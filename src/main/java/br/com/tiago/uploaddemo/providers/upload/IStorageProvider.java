package br.com.tiago.uploaddemo.providers.upload;

public interface IStorageProvider {
    String save(String fileName, byte[] file);
}
