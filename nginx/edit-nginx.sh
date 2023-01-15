cat >> nginx/dockerfile <<EOF
RUN echo 'server { \\n \\\\
    listen 80; \\n \\\\
    location / { \\n \\\\
        proxy_set_header X-Real-IP \\$remote_addr; \\n \\\\
        proxy_pass http://app:5000; \\n \\\\
    } \\n \\\\
}' > /etc/nginx/conf.d/default.conf
EOF

docker build -t 1372022/nginx -f nginx/dockerfile .
docker login -u 1372022 -p dckr_pat_lnjpVpoTSiM1kjoVCdTstLLJhoU
docker push 1372022/nginx