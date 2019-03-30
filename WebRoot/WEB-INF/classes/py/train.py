# -*- coding: utf-8 -*-

import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
import matplotlib.pyplot as plt
from sklearn.externals import joblib
from sys import argv

# 使用的特征列
FEAT_COLS = ['NCEE', 'readtime', 'wordtime']


def main():
    """
        主函数
    """
    
    RES_PATH=argv[1]
    #资源文件
    DATA_FILE=RES_PATH+"\\project.csv"
    #训练模型
    MODEL_FILE=argv[2]+"\\demo_model.m"

    grade_data = pd.read_csv(DATA_FILE, usecols=FEAT_COLS + ['result'])
    

    X = grade_data[FEAT_COLS].values
    y = grade_data['result'].values

    # 分割数据集
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=1 / 3, random_state=10)

    # 建立线性回归模型
    linear_reg_model = LinearRegression()
    # 模型训练
    linear_reg_model.fit(X_train, y_train)
    joblib.dump(linear_reg_model, MODEL_FILE)
    print("结束")





if __name__ == '__main__':
     main()
  
