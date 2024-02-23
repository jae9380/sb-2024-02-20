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

  * 중단 배포, v3 (test)
```
  # 기존에 실행된 컨테이너 제거
  docker stop sb_2024_02_20_1_1
  docker rm sb_2024_02_20_1_1
  
  # 새 도커 이미지 PULL
  docker pull ghcr.io/jhs512/sb_2024_02_20
  
  # 생성된 이미지 실행
  docker run \
  --name=sb_2024_02_20_1_1 \
  -p 8080:8080 \
  -v /docker_projects/sb_2024_02_20_1/volumes/gen:/gen \
  --restart unless-stopped \
  -e TZ=Asia/Seoul \
  -d \
  ghcr.io/jhs512/sb_2024_02_20
  
  # 태그가 none 인 이미지(예전에는 latest 였으니, docker pull 에 의해서 새로운 latest 태그를 가진 이미지가 생겨서, 더 이상 사용안되는 것들) 제거
  docker rmi $(docker images -f "dangling=true" -q)
```

이제는 원격에 PULL -> 기존 컨테이너 제거 -> 새로운 이미지 PULL -> 생성된 이미지 실행하면 된다.

깃허브 액션에서 모든것을 자동으로 할려면 ssh에 들어가서 명령어를 날려야 하는데
지금 ssh를 안쓰고 ssm이라는 방식을 사용했다.

액션이 끝나면 ec2에 명령을 날리기위해 

APPLICATION_SECRET_YML, AWS_ACCESS_KEY_ID, AWS_REGION, AWS_SECRET_ACCESS_KEY 에 대한 정ㅂ보도 원격에 저장