cmake_minimum_required(VERSION 3.28)
project(Lab_9_10_24_04_2024)

set(CMAKE_CXX_STANDARD 17)

add_executable(Lab_9_10_24_04_2024 main.cpp
        Domain/Entity.cpp
        Domain/Entity.h
        Repository/Repo.cpp
        Repository/Repo.h
        Tests/Tests.cpp
        Tests/Tests.h
        Service/ServiceBilet.cpp
        Service/ServiceBilet.h
        UI/Console.cpp
        UI/Console.h
        Repository/FileRepo.cpp
        Repository/FileRepo.h
        Domain/Vali_and_Exept.h
        Domain/vali.h)
