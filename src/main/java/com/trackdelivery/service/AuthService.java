package com.trackdelivery.service;

import com.trackdelivery.dto.request.AuthRequest;
import com.trackdelivery.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse authenticate(AuthRequest request);
}
