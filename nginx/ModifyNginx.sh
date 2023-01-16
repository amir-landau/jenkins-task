cat >> nginx/dockerfile <<EOF
RUN echo 'server { \n \\
    listen 80; \n \\
    location / { \n \\
        proxy_pass http://app:5000; \n \\
        proxy_set_header X-Real-IP \$remote_addr; \n \\
    } \n \\
}' > /etc/nginx/conf.d/default.conf
EOF

