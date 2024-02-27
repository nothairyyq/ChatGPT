# dev-ops
docker container cp Nginx:/etc/nginx/nginx.conf /mnt/f/ChatGPT/dev-ops/nginx/
docker container cp Nginx:/etc/nginx/conf.d/default.conf /mnt/f/ChatGPT/dev-ops/nginx/conf/conf.d
docker container cp Nginx:/usr/share/nginx/html/index.html /mnt/f/ChatGPT/dev-ops/nginx/html


docker run \
--restart always \
--name Nginx \
-d \
-v /mnt/f/ChatGPT/dev-ops/nginx/html:/usr/share/nginx/html \
-v /mnt/f/ChatGPT/dev-ops/nginx/conf/conf.d/default.conf:/etc/nginx/conf.d/default.conf \
-v /mnt/f/ChatGPT/dev-ops/nginx/conf/nginx.conf:/etc/nginx/nginx.conf \
-p 80:80 \
nginx


