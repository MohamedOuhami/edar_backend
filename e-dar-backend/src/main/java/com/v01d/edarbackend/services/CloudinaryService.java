package com.v01d.edarbackend.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    @Autowired
    private Cloudinary cloudinary;

    public String uploadImage(File file) throws IOException {
        Map<?, ?> result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        return (String) result.get("secure_url");
    }
}
