import pandas as pd
from sys import argv

def main():
    RES_PATH=argv[1]
    if RES_PATH.strip()=='':
       print("路径参数为空")
       return
    data = pd.read_csv(RES_PATH+"\\grade.csv")
    print(data)


if __name__ == '__main__':
        main()
