* 중단 배포
  소프트웨어나 서비스의 새로운 버전이나 업데이트를 배포할 때, 
  서비스를 일시적으로 중단하고 업데이트를 적용하는 프로세스를 의미


  * v1
```
    # 소스코드 폴더로 이동
    cd /docker_projects/sb_2024_02_20_1/source
    # 소스코드 최신화
    git pull origin main
    
    # 도커 이미지 생성(v2)
    docker build -t sb_2024_02_20_1:2 .
    
    # 기존에 실행된 컨테이너 제거
    docker stop sb_2024_02_20_1_1
    docker rm sb_2024_02_20_1_1
    
    # 생성된 이미지 실행
    docker run \
    --name=sb_2024_02_20_1_1 \
    -p 8080:8080 \
    -v /docker_projects/sb_2024_02_20_1/volumes/gen:/gen \
    --restart unless-stopped \
    -e TZ=Asia/Seoul \
    -d \
    sb_2024_02_20_1:2
    
    # 기존 이미지 제거
    docker rmi sb_2024_02_20_1:1
```

  * v2
```
  # ghcr.io 에 로그인
  docker login ghcr.io -u USERNAME -p YOUR_TOKEN
  
  # 기존에 실행된 컨테이너 제거
  docker stop sb_2024_02_20_1_1
  docker rm sb_2024_02_20_1_1

  # 생성된 이미지 실행
  docker run \
    --name=sb_2024_02_20_1_1 \
    -p 8080:8080 \
    -v /docker_projects/sb_2024_02_20_1/volumes/gen:/gen \
    --restart unless-stopped \
    -e TZ=Asia/Seoul \
    -d \
    ghcr.io/jhs512/sb_2024_02_20
    
  # 기존 이미지 제거
  docker rmi sb_2024_02_20_1:2
```
```
  # 소스코드 폴더로 이동
  cd /docker_projects/sb_2024_02_20_1/source
  # 소스코드 최신화
  git pull origin main

  # 도커 이미지 생성(v2)
  docker build -t sb_2024_02_20_1:2 .
  
  이 부분은 GitHub Action으로 자동화로 설정 
```