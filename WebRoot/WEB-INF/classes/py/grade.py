import pandas as pd
from sys import argv

def main():
	RES_PATH=argv[1]
	data = pd.read_csv(RES_PATH+"\\grade.csv")
	print(data)


if __name__ == '__main__':
        main()
