package cw12;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import cw12.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;




public class Myframe extends JFrame  {
    
    JLabel label, label2;
    JPanel mainPanel, topPanel, panel, panel2, panel3;
    JComboBox<String> combobox;
    JTable table;
    JTextArea text;
    JTextField t1;
    JButton b1, shoppingCartButton;
    
    
    private ArrayList<Product> productList = new ArrayList<>();
    private int isExistingUser = 0;
    private ShoppingCart shoppingCart = new ShoppingCart();

    //customsellrenderer for highliting the rows with low product availability...........................
    private class CustomCellRenderer extends DefaultTableCellRenderer {
        private final java.util.List<Integer> redRows = new ArrayList<>();

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            Product product = productList.get(row);
            if (product.getProductNumberOfAvailableItems() < 3) {
                redRows.add(row);
                component.setBackground(Color.RED);
            } else {
                component.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            }

            return component;
        }

        public void clearRedRows() {
            redRows.clear();
        }

        public java.util.List<Integer> getRedRows() {
            return redRows;
        }
    }

    CustomCellRenderer customCellRenderer = new CustomCellRenderer();
    
    //create my frame constructor....................................................................
    Myframe() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        shoppingCartButton = new JButton("Shopping Cart");
        topPanel.add(shoppingCartButton);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(300,100));
        panel.setLayout(new FlowLayout());

        label2 = new JLabel();
        panel.add(label2);
        label2.setText("Selected Product Category");
        panel.add(label2);
        String[] products ={"All","Electronic","Clothes"};
        combobox= new JComboBox<>(products);
        combobox.setBounds(10, 10, 10, 10);
        panel.add(combobox);

        panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(300,100));
        panel2.setLayout(new FlowLayout());
        panel2.setBackground(Color.green);
        t1= new JTextField();
        t1.setText("selected lists");
        text=new JTextArea();
        text.setSize(50, 100);  // Set your desired height
        text.setRows(10);  // Set your desired number of rows
        b1 = new JButton();
        b1.setText("Add to cart");
        b1.setSize(10, 10);
        b1.setLayout(new BorderLayout());
        panel2.add(t1);
        panel2.add(text);
        panel2.add(b1);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Product ID");
        model.addColumn("Product Name");
        model.addColumn("Product Price");
        model.addColumn("Product Type");

        table = new JTable(model);
        table.setDefaultRenderer(Object.class, customCellRenderer);
        ArrayList<Product> allProductList;

        combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedProductType = combobox.getSelectedItem().toString();

                updateTableAfterFiltering(getFilteredProductList(selectedProductType));
            }
        });

        shoppingCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openShoppingCartFrame();
            }
        });

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    TableModel model = table.getModel();
                    String productId = model.getValueAt(selectedRow, 0).toString();
                    String productName = model.getValueAt(selectedRow, 1).toString();
                    String productPrice = model.getValueAt(selectedRow, 2).toString();
                    String productType = model.getValueAt(selectedRow, 3).toString();

                    String additionalDetails = "";
                    if ("Cloth".equals(productType)) {
                        Clothes selectedCloth = getClothesById(productId);
                        additionalDetails = "Size: " + selectedCloth.getProductSize() +
                                "\nColor: " + selectedCloth.getProductColour();
                    } else if ("Electronic".equals(productType)) {
                        Electronic selectedElectronic = getElectronicById(productId);
                        additionalDetails = "Brand: " + selectedElectronic.getProductBrand() +
                                "\nWarranty Period: " + selectedElectronic.getProductWarrantyPeriod();
                    }

                    String selectedProductDetails = "ID: " + productId + "\nName: " + productName +
                            "\nPrice: " + productPrice + "\nType: " + productType + "\n\n" + additionalDetails;
                    text.setText(selectedProductDetails);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); // Add this line

        panel3 = new JPanel();
        panel3.setPreferredSize(new Dimension(300,350));
       
        panel3.setLayout(new BorderLayout());
        panel3.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(panel, BorderLayout.CENTER);

        this.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.NORTH);
        this.add(panel2, BorderLayout.SOUTH);
        this.add(panel3, BorderLayout.CENTER);

        //details pannel with selected product information
        JLabel selectedProductLabel = new JLabel("Selected Product - Details");
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BorderLayout());
        detailsPanel.add(selectedProductLabel, BorderLayout.NORTH);
        detailsPanel.add(new JScrollPane(text), BorderLayout.CENTER);
        JButton addToCartButton = new JButton("Add to Shopping Cart");
        detailsPanel.add(addToCartButton, BorderLayout.SOUTH);

        this.add(detailsPanel, BorderLayout.SOUTH);

        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToShoppingCart();
            }
        });
    }

    private Clothes getClothesById(String productId) {
        for (Product product : productList) {
            if (product instanceof Clothes && product.getProductId().equals(productId)) {
                return (Clothes) product;
            }
        }
        return null;
    }

    private Electronic getElectronicById(String productId) {
        for (Product product : productList) {
            if (product instanceof Electronic && product.getProductId().equals(productId)) {
                return (Electronic) product;
            }
        }
        return null;
    }

    private void openShoppingCartFrame() {
        SwingUtilities.invokeLater(() -> {
            ShoppingCartFrame shoppingCartFrame = new ShoppingCartFrame(shoppingCart, isExistingUser);
            shoppingCartFrame.setLocationRelativeTo(Myframe.this);
            shoppingCartFrame.setVisible(true);
        });
    }

    private void addToShoppingCart() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            TableModel model = table.getModel();
            String productId = model.getValueAt(selectedRow, 0).toString();

            Product selectedProduct = getProductById(productId);

            int availableItems = selectedProduct.getProductNumberOfAvailableItems();
            if (availableItems > 0) {
                selectedProduct.setProductNumberOfAvailableItems(availableItems - 1);

                shoppingCart.addToCart(selectedProduct);

                updateTable(productList, isExistingUser);
            } else {
                JOptionPane.showMessageDialog(this, "Sorry, this product is out of stock.", "Out of Stock", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private Product getProductById(String productId) {
        for (Product product : productList) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    private ArrayList<Product> getFilteredProductList(String selectedProductType) {
        ArrayList<Product> filteredList = new ArrayList<>();

        for (Product product : productList) {
            if (selectedProductType.equals("All")) {
                filteredList.add(product);
            } else if (selectedProductType.equals("Electronic") && product instanceof Electronic) {
                filteredList.add(product);
            } else if (selectedProductType.equals("Clothes") && product instanceof Clothes) {
                filteredList.add(product);
            }
        }

        return filteredList;
    }

    public void updateTableAfterFiltering(ArrayList<Product> productList) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (Product product : productList) {
            if (product instanceof Clothes) {
                Object[] rowData = new Object[]{
                        product.getProductId(),
                        product.getProductName(),
                        product.getProductPrice(),
                        "Cloth"
                };
                model.addRow(rowData);
                if (product.getProductNumberOfAvailableItems() < 3) {
                    int rowIndex = model.getRowCount() - 1;
                    for (int i = 0; i < rowData.length; i++) {
                        model.setValueAt(rowData[i], rowIndex, i);
                        table.setRowSelectionInterval(rowIndex, rowIndex);
                        table.setSelectionBackground(Color.RED);
                    }
                }
            } else if (product instanceof Electronic) {
                Object[] rowData = new Object[]{
                        product.getProductId(),
                        product.getProductName(),
                        product.getProductPrice(),
                        "Electronic"
                };
                model.addRow(rowData);
                if (product.getProductNumberOfAvailableItems() < 3) {
                    int rowIndex = model.getRowCount() - 1;
                    for (int i = 0; i < rowData.length; i++) {
                        model.setValueAt(rowData[i], rowIndex, i);
                        table.setRowSelectionInterval(rowIndex, rowIndex);
                        table.setSelectionBackground(Color.RED);
                    }
                }
            }
        }
    }



    public void updateTable(ArrayList<Product> productList, int isExistingUser) {

        this.productList = productList;
        this.isExistingUser = isExistingUser;

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        // Add product data to the table
        for (Product product : productList) {
            if (product instanceof Clothes) {
                Object[] rowData = new Object[]{
                        product.getProductId(),
                        product.getProductName(),
                        product.getProductPrice(),
                        "Cloth"
                };
                model.addRow(rowData);
                if (product.getProductNumberOfAvailableItems() < 3) {
                    int rowIndex = model.getRowCount() - 1;
                    for (int i = 0; i < rowData.length; i++) {
                        model.setValueAt(rowData[i], rowIndex, i);
                        table.setRowSelectionInterval(rowIndex, rowIndex);
                        table.setSelectionBackground(Color.RED);
                    }
                }
            } else if (product instanceof Electronic) {
                Object[] rowData = new Object[]{
                        product.getProductId(),
                        product.getProductName(),
                        product.getProductPrice(),
                        "Electronic"
                };
                model.addRow(rowData);
                if (product.getProductNumberOfAvailableItems() < 3) {
                    int rowIndex = model.getRowCount() - 1;
                    for (int i = 0; i < rowData.length; i++) {
                        model.setValueAt(rowData[i], rowIndex, i);
                        table.setRowSelectionInterval(rowIndex, rowIndex);
                        table.setSelectionBackground(Color.RED);
                    }
                }
            }
        }
    }
}