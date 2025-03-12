cmake_minimum_required(VERSION 3.28)
project(Lab7_8_OOP)

set(CMAKE_CXX_STANDARD 17)

add_executable(Lab7_8_OOP main.cpp
        Repository/Repo.cpp
        Repository/Repo.h
        Domain/Entities.cpp
        Domain/Entities.h
        Tests/Tests.cpp
        Tests/Tests.h
        Service/Service.cpp
        Service/Service.h
        UI/UI.cpp
        UI/UI.h
        Repository/FileRepo.cpp
        Repository/FileRepo.h
)
