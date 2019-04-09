# -*- coding: utf-8 -*-

import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
import matplotlib.pyplot as plt
from sklearn.externals import joblib
from sys import argv

# 使用的特征列
FEAT_COLS = []
# 结果列
RESULTCOL = []
#资源文件
RES_PATH = ""
#模型文件
MODEL_FILE = ""



def build_feat_cols():
    global RES_PATH
    #读取列信息
    global MODEL_FILE
    global FEAT_COLS
    global RESULTCOL
    RES_PATH = argv[1]
    MODEL_FILE = argv[2]
    heads=argv[3]
    FEAT_COLS = heads.split(',')
    RESULTCOL = FEAT_COLS[-1] 
    del FEAT_COLS[-1]
    print("RES_PATH:",RES_PATH)
    print("MODEL_FILE:",MODEL_FILE)
    print("FEAT_COLS:",FEAT_COLS)
    print("RESULTCOL:",RESULTCOL)



def main():
    """
        主函数
    """
    #待生成的训练模型
    grade_data = pd.read_csv(RES_PATH, usecols=FEAT_COLS + [RESULTCOL])

    X = grade_data[FEAT_COLS].values
    y = grade_data[RESULTCOL].values

    # 分割数据集
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=1 / 3, random_state=10)

    # 建立线性回归模型
    linear_reg_model = LinearRegression()
    
    # 模型训练
    linear_reg_model.fit(X_train, y_train) 
    joblib.dump(linear_reg_model, MODEL_FILE)
     
    score = linear_reg_model.score(X_test, y_test)
    print('模型的R2值', score)
    
    i = 0
    # 第i行所有列（即一条数据的特征数据）
    single_test_feat = X_test[i, :]
    y_true = y_test[i]
    y_pred = linear_reg_model.predict([single_test_feat])
    print('样本特征:', single_test_feat)
    print('真实结果数据：{}，预测结果数据：{}'.format(y_true, y_pred))
    print("模型训练结束")
   


if __name__ == '__main__':
     build_feat_cols()
     main()
  
